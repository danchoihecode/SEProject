import {getServerSession} from "next-auth/next";
import {authOption} from "@/configs/next-auth-config";
import {Session} from "next-auth";
import ChattingBox from "@/components/chatting-box";
import AddFriendButton from "@/components/add-friend-button";
import Box from "@mui/material/Box";
import {SelectedRoomContextProvider} from "@/context/selected-room-context";
import {SideBar} from "@/components/side-bar";
import Divider from "@mui/material/Divider";
import {Item} from "@/components/item-list";
import {getConversationList, getUserName} from "@/server/chatting-data";

export default async function TestPage() {
    const chats:Item[] = await getConversationList()
    const session = await getServerSession(authOption as any) as Session;
    const name = await getUserName();
    return ( <Box sx={{display:'flex',height:'100%'}} >
        <SelectedRoomContextProvider>
            <SideBar chats={chats}/>
            <Divider orientation="vertical"/>

            {/*<StartConversation onClickStart={() =>{}}/>*/}
            <ChattingBox senderName={name} senderUserId={session.id}/>
        </SelectedRoomContextProvider>
    </Box>)
}