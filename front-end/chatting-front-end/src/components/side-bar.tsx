'use client'
import Box from "@mui/material/Box";
import themeConfig from "@/configs/themeConfig";

import Divider from "@mui/material/Divider";
import {InputAdornment, TextField} from "@mui/material";
import Magnify from "mdi-material-ui/Magnify";
import ItemList, {Item} from "@/components/item-list";
import {getConversationList} from "@/server/chatting-data";
import {ChangeEvent, useState} from "react";
import {searchFriend} from "@/server/search-friends";


export const SideBar = () => {

    const chats:Item[] = getConversationList();

    const [displayChats,setDisplayChats] = useState(chats) ;

    const handleSearchInput = async (event: ChangeEvent<HTMLInputElement>) => {
        if (event.target.value !== '') {
            const filedChats = chats.filter(chat => chat.name.includes(event.target.value))
            if (filedChats.length !== 0) {
                setDisplayChats(filedChats)
            } else {
                const peopleFounded: Item[] = await searchFriend(event.target.value)
                setDisplayChats(peopleFounded)
            }
        } else {
            setDisplayChats(chats)
        }
    }

    return (
        <Box sx={{width: themeConfig.navigationSize}}>
            <TextField
                fullWidth
                size='small'
                sx={{ '& .MuiOutlinedInput-root': { borderRadius: 4 },p:'10px' }}
                InputProps={{
                    startAdornment: (
                        <InputAdornment position='start'>
                            <Magnify fontSize='small' />
                        </InputAdornment>
                    )
                }}
                onChange={handleSearchInput}
            />
            <Divider/>
            <ItemList chats={displayChats}/>
        </Box>
    )
}