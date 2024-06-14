import Box from "@mui/material/Box";
import StartConversation from "@/components/start-conversation";
import {SideBar} from "@/components/side-bar";
import {SelectedRoomContextProvider} from "@/context/selected-room-context";
import {Item} from "@/components/item-list";
import {getConversationList} from "@/server/chatting-data";

export default async function ChatPage(){
    const chats:Item[] = await getConversationList()
    return(
        <Box sx={{display:'flex',height:'100%'}} >
            <SelectedRoomContextProvider>
                <SideBar chats={chats}/>
                <StartConversation onClickStart={() =>{}}/>
            </SelectedRoomContextProvider>
        </Box>
    )
}