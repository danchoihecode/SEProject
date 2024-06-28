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
import {createGroup} from "@/server/add-group";
import {useRouter} from "next/navigation";


interface SideBarProps{
    chats:Item[],
    listFriends:Friend[]
}

export type Friend = {
    id: string;
    name: string;
};

export const SideBar = (props:SideBarProps) => {

    const {chats} = props;
    const [showAddGroupForm, setShowAddGroupForm] = useState(false);
    const router = useRouter();

    const handleCreateGroup = (groupName: string, members: Friend[]) => {
        createGroup({groupName:groupName,Ids:members}).then(
            r =>{
                if (r){
                    console.log('New group created:', groupName ,members);
                    router.refresh()
                }
            }
        )
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
            <Box sx={{ display: 'flex', alignItems: 'center', p: '5px' }}>
                <TextField
                    size='small'
                    sx={{ '& .MuiOutlinedInput-root': { borderRadius: 4 }, width: '80%' }}
                    InputProps={{
                        startAdornment: (
                            <InputAdornment position='start'>
                                <Magnify fontSize='small' />
                            </InputAdornment>
                        )
                    }}
                    onChange={handleSearchInput}
                />
                <Box sx={{ display: 'flex', alignItems: 'center',width:'20%',justifyContent:'center' }}>
                       <AddGroupButton handleAddGroupClick={handleAddGroupClick} />
                </Box>
            </Box>
            <Divider/>
            <ItemList chats={displayChats}/>
            <GroupPopUp  handleCreateGroup={handleCreateGroup} handleCloseForm = {handleCloseForm} listFriends={props.listFriends} showAddGroupForm={showAddGroupForm} />
        </Box>
    )
}