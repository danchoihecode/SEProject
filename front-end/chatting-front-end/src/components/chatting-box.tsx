'use client'
import { useState, useEffect } from 'react';
import { Client } from '@stomp/stompjs';
import SockJS from 'sockjs-client/dist/sockjs';
import { Button, TextField, Box } from '@mui/material';
import ChatMessage from "@/components/chat-message";

interface MessageProps {
    senderUserId:string,
    conversationId:string,

}


function ChattingBox(props:MessageProps) {
    const [messages, setMessages] = useState([]);
    const [client, setClient] = useState<Client>(null);
    const [newMessage, setNewMessage] = useState('');


    useEffect(() => {
        const newClient = new Client({
            webSocketFactory: () => new SockJS('http://localhost:8080/ws',{
            }),
            onConnect: () => {
                const joinMessage = {
                    conversationId: props.conversationId,
                    senderUserId: props.senderUserId,
                    messageType: 'CONNECT',
                };
                newClient.publish({ destination: '/app/chat/user-adding'
                    , body: JSON.stringify(joinMessage)
                });
                console.log(joinMessage); // Log the join message
                newClient.subscribe('/topic/public', message => {
                    const newMessage = JSON.parse(message.body);
                    setMessages(prevMessages => [...prevMessages, newMessage]);
                });
            },
            onDisconnect: () => {
                if (newClient.connected) {
                    const leaveMessage = {
                        conversationId: props.conversationId,
                        senderUserId: props.senderUserId,
                        messageType: 'DISCONNECT',
                    };
                    newClient.publish({ destination: '/app/chat/user-adding', body: JSON.stringify(leaveMessage) });
                    console.log(leaveMessage); // Log the leave message
                }
            },
            onWebSocketClose: () => {
            }
        });

        newClient.activate();
        setClient(newClient);
        //
        return  () => {
             newClient.deactivate();
        };
    }, []);


    const sendMessage = () => {
        if (newMessage && client) {
            const chatMessage = {
                messageType: 'CHAT',
                conversationId: props.conversationId,
                senderUserId: props.senderUserId,
                messageContent: newMessage,
            };
            client.publish({ destination: '/app/chat/message-sending', body: JSON.stringify(chatMessage) });
            console.log(chatMessage);
            setNewMessage('')
        }
    };

    return (
        <Box sx={{width: '100%', margin: '2.5%'}}>
            <Box display="flex" flexDirection="column" justifyContent="center" >
                <Box sx={{height: '75.5vh', overflow: 'auto', width: '100%'}}>
                    {messages.map((message, index) => (
                        <ChatMessage key={index} message={message} username={props.senderUserId}/>
                    ))}
                </Box>
                <Box sx={{display:'flex',flexDirection:'row', ml:'10%'}}  >
                    <TextField
                        sx={{
                            width: '80%',
                            height: '10px',
                            '& .MuiOutlinedInput-root': {
                                borderRadius: '36px',
                                '& fieldset': {
                                    borderColor: 'gray',
                                },
                                '& input': {
                                    height: '10px',
                                },
                            },
                        }}

                        value={newMessage}
                        onChange={e =>{setNewMessage(e.target.value)}}
                        placeholder="Type a message..."
                    />
                    <Box marginLeft={2}>
                        <Button
                            variant="contained"
                            color="primary"
                            sx={{
                                width: '94px',
                                height: '42px',
                                borderRadius: '36px',
                            }}
                            onClick={sendMessage}>
                            Send
                        </Button>
                    </Box>
                </Box>
            </Box>
        </Box>
    );
}

export default ChattingBox;