'use client'
import {List, ListItemAvatar, ListItemButton, ListItemText, Typography} from "@mui/material";
import Avatar from "@mui/material/Avatar";
import Box from "@mui/material/Box";
import {useContext} from "react";
import {SelectedRoomContext} from "@/context/selected-room-context";

export type Item = {
    conversationID:string,
    conversationName:string,
    isGroup?:boolean,
    isRead:boolean
}

interface ItemListProps{
    chats:Item[]
}
const ItemList = (props:ItemListProps) =>{
    const {chats} = props;
    const maxLength = 20

    if(chats.length === 0){
        return <List component='nav'>
            <Typography variant='h6' justifyContent='center' sx={{
                display:'flex'
            }}>
                Not Found!
            </Typography>
        </List>
    }

    const {selectedIndex,setSelectedIndex} = useContext(SelectedRoomContext)

    const listItems = chats.map(chat => {
        return (
            <ListItemButton
                key={chat.conversationID}
                alignItems='flex'
                sx={{borderRadius:'5px',mb:'5px'}}
                selected={selectedIndex === chat.conversationID}
                onClick={()=>setSelectedIndex(chat.conversationID)}
            >
                <ListItemAvatar>
                    <Avatar alt='tet1' />
                </ListItemAvatar>
                <ListItemText sx={{height:45}}>
                    <Typography sx={chat.isRead?{fontWeight:'bold'}:null}>
                        {chat.conversationName.length > maxLength
                            ? chat.conversationName.slice(0, maxLength) + '...'
                            : chat.conversationName}
                    </Typography>
                    {/*<Typography sx={chat.isRead?{fontWeight:'bold'}:null} >*/}
                    {/*    {chat.latestMessage?*/}
                    {/*        chat.latestMessage.length > maxLength ? chat.latestMessage.slice(0, maxLength) + '...' : chat.latestMessage*/}
                    {/*        : null}*/}
                    {/*</Typography>*/}
                </ListItemText>
                {chat.isRead?<Box sx={{
                    bgcolor:'primary.light',
                    borderRadius:'50px',
                    height:'10px',
                    width:'10px'}}/>:null}
            </ListItemButton>
        )
    })
    return (
        <List component='nav'>
            {listItems}
        </List>
    )
}

export default ItemList