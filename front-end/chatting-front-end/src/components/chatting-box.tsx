'use client'
import {useState, useEffect, useContext, useRef, Suspense} from 'react';
import SockJS from 'sockjs-client/dist/sockjs';
import { Client } from '@stomp/stompjs';
import { Button, TextField, Box } from '@mui/material';
import ChatMessage from "@/components/chat-message";
import {SelectedRoomContext} from "@/context/selected-room-context";
import StartConversation from "@/components/start-conversation";

import {getMessageHistory} from "@/server/history";

interface MessageProps {
    senderUserId:string,
    senderName: string,
    token:string
}

export interface MessageHistory {
    messageType:string,
    messageContent:string,
    senderUserId:string,
    conversationId:string,
    senderName:string
}

function ChattingBox(props:MessageProps) {

    const [messages, setMessages] = useState<MessageHistory[]>([]);
    const [client, setClient] = useState<Client>(null);
    const [newMessage, setNewMessage] = useState('');
    const {selectedIndex,setSelectedIndex} = useContext(SelectedRoomContext)
    const messagesEndRef = useRef<HTMLDivElement|null>(null);

    useEffect(() => {
        if(selectedIndex !== '0'){
            getMessageHistory({conservationId:selectedIndex}).then(
                r =>{
                    console.log(r)
                    if(r){
                        setMessages(r)
                    }
                }
            )
        }
    }, [selectedIndex]);

    useEffect(() => {
        const newClient = new Client({
            webSocketFactory: () => new SockJS('http://localhost:8080/ws',{
            }),
            onConnect: () => {
                const joinMessage = {
                    conversationId: selectedIndex,
                    senderUserId: props.senderUserId,
                    senderName: props.senderName,
                    messageType: 'CONNECT',
                };
                newClient.publish({ destination: '/app/message'
                    , body: JSON.stringify(joinMessage)
                });
                newClient.subscribe('/topic/private/' +selectedIndex, message => {
                    const newMessage = JSON.parse(message.body);
                    setMessages(prevMessages => [...prevMessages, newMessage]);
                });
            },
            onDisconnect: () => {
                if (newClient.connected) {
                    const leaveMessage = {
                        conversationId: selectedIndex,
                        senderUserId: props.senderUserId,
                        senderName: props.senderName,
                        messageType: 'DISCONNECT',
                    };
                    newClient.publish({ destination: '/app/message', body: JSON.stringify(leaveMessage) });
                }
            }
        });

        newClient.activate();
        setClient(newClient);
        //
        return  () => {
            newClient.deactivate();
            setMessages([])
        };
    }, [selectedIndex]);


    const sendMessage = () => {
        if (newMessage && client) {
            const chatMessage = {
                messageType: 'CHAT',
                conversationId: selectedIndex,
                senderUserId: props.senderUserId,
                senderName: props.senderName,
                messageContent: newMessage,
            };
            client.publish({ destination: '/app/message', body: JSON.stringify(chatMessage) });
            setNewMessage('')
        }
    };

    useEffect(() => {
        // Scroll to the bottom whenever messages update
        if(messagesEndRef.current instanceof HTMLDivElement)
            messagesEndRef.current.scrollIntoView({
                behavior: 'smooth',
                block: 'end',  // Scroll to the bottom of the element
                inline: 'nearest'  // Scroll to the nearest edge of the element
            });
    }, [messages]);



    if(selectedIndex === '0')
        return <StartConversation onClickStart={()=>{}}/>


    return (
        <Box sx={{width: '100%', margin: '2.5%'}}>
            <Box display="flex" flexDirection="column" justifyContent="center" >
                <Box sx={{height: '75.5vh', overflow: 'auto', width: '100%'}}>
                    {messages.map((message, index) => (
                        <ChatMessage key={index} message={message} username={props.senderUserId}/>
                    ))}
                    <div ref={messagesEndRef}/>
                </Box>
                <Box sx={{display: 'flex',flexDirection:'row', ml:'10%'}}  >
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