'use client'
import Box from "@mui/material/Box";
import themeConfig from "@/configs/themeConfig";

import Divider from "@mui/material/Divider";
import {InputAdornment, TextField} from "@mui/material";
import Magnify from "mdi-material-ui/Magnify";
import ItemList, {Item} from "@/components/item-list";
import {ChangeEvent, Key, useState} from "react";
import {searchFriend} from "@/server/search-friends";
import AddGroupButton from "./add-group-button";
import GroupPopUp from "./group-pop-up";


interface SideBarProps{
    chats:Item[]
}

export type Friend = {
    id: Key;
    name: String;
};

export const SideBar = (props:SideBarProps) => {

    const {chats} = props;
    const [showAddGroupForm, setShowAddGroupForm] = useState(false);


    const listFriends: Friend[] = [
        { id: '1', name: 'John Doe' },
        { id: '2', name: 'Jane Smith' },
        // Add more friends here
    ];
    const handleCreateGroup = (groupName: string, members: Friend[]) => {
        // Create a new chat item for the group
        const newChat: Item = {
            conversationName: groupName,
            conversationID: String(displayChats.length + 10),
            isRead: false,
            isGroup :true,
            isFriend : false,
        };

        // Update the chats and displayChats states
        setDisplayChats((prevDisplayChats) => [...prevDisplayChats, newChat]);
        console.log('New group created:', groupName ,members);
    };

    const [displayChats,setDisplayChats] = useState(chats) ;
    const handleAddGroupClick = () => {
        setShowAddGroupForm(true);
    };
    const handleCloseForm = () => {
        setShowAddGroupForm(false);
    };
    const handleSearchInput = async (event: ChangeEvent<HTMLInputElement>) => {
        if (event.target.value !== '') {
            const filedChats = chats.filter(chat => chat.conversationName.includes(event.target.value))
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
        <Box sx={{width: themeConfig.navigationSize, borderColor:'black'}}>
            <Box sx={{ display: 'flex', alignItems: 'center', justifyContent: 'space-between', p: '5px' }}>
                <TextField
                    size='small'
                    sx={{ '& .MuiOutlinedInput-root': { borderRadius: 4 }, width: '70%' }}
                    InputProps={{
                        startAdornment: (
                            <InputAdornment position='start'>
                                <Magnify fontSize='small' />
                            </InputAdornment>
                        )
                    }}
                    onChange={handleSearchInput}
                />
                <Box sx={{ display: 'flex', alignItems: 'center' }}>
                       <AddGroupButton handleAddGroupClick={handleAddGroupClick} />
                </Box>
            </Box>
            <Divider/>
            <ItemList chats={displayChats}/>
            <GroupPopUp handleCreateGroup={handleCreateGroup} handleCloseForm = {handleCloseForm} listFriends={listFriends} showAddGroupForm={showAddGroupForm} />
        </Box>
    )
}