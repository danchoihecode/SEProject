'use client'
import {List, ListItem, ListItemAvatar, ListItemButton, ListItemText, Typography} from "@mui/material";
import Avatar from "@mui/material/Avatar";
import React, {useContext, useState} from "react";
import {SelectedRoomContext} from "@/context/selected-room-context";
import AddFriendButton from "@/components/add-friend-button";
import AccountAvatar from "@/components/letter-avatar";

export type Item = {
    conversationID:string,
    conversationName:string,
    isGroup?:boolean,
    isRead:boolean,
    isFriend:boolean
}

interface ItemListProps{
    chats:Item[]
}
const ItemList = (props:ItemListProps) =>{
    const {chats} = props;
    const maxLength = 20 ;

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
        // need to modify isAdded API ?
        // need to modify isFriend API ?
        if(!chat.isFriend){
            return <ListItem
                key={chat.conversationID}
                alignItems='flex'
                sx={{borderRadius:'5px',mb:'5px'}}
            >
                <ListItemAvatar>
                    <AccountAvatar name={chat.conversationName}/>
                </ListItemAvatar>
                <ListItemText sx={{height:45}}>
                    <Typography sx={chat.isRead?{fontWeight:'bold'}:null}>
                        {chat.conversationName.length > maxLength
                            ? chat.conversationName.slice(0, maxLength) + '...'
                            : chat.conversationName}
                    </Typography>
                </ListItemText>
                <AddFriendButton isFriend={chat.isFriend} />
            </ListItem>
        }
        return (
            <ListItemButton
                key={chat.conversationID}
                alignItems='flex'
                sx={{borderRadius:'5px',mb:'5px'}}
                selected={selectedIndex === chat.conversationID}
                onClick={()=>setSelectedIndex(chat.conversationID)}
            >
                <ListItemAvatar>
                    <AccountAvatar name={chat.conversationName}/>
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

                <AddFriendButton isFriend={chat.isFriend} />
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