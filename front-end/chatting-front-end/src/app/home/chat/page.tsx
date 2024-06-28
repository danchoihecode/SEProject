import Box from "@mui/material/Box";
import {SideBar} from "@/components/side-bar";
import {SelectedRoomContextProvider} from "@/context/selected-room-context";
import {Item} from "@/components/item-list";
import {getConversationList, getUserName} from "@/server/chatting-data";
import {getServerSession} from "next-auth/next";
import {authOption} from "@/configs/next-auth-config";
import {Session} from "next-auth";
import ChattingBox from "@/components/chatting-box";
import Divider from "@mui/material/Divider";
import {getMessageHistory} from "@/server/history";

export default async function ChatPage(){
    const chats:Item[] = await getConversationList()
    const session = await getServerSession(authOption as any) as Session;
    const name = await getUserName();
    return(
        <Box sx={{display:'flex',height:'100%'}} >
            <SelectedRoomContextProvider>
                <SideBar chats={chats}/>
                <Divider orientation="vertical"/>
                <ChattingBox senderName={name} senderUserId={session.id} token={session.access_token}/>
            </SelectedRoomContextProvider>
        </Box>
    )
}