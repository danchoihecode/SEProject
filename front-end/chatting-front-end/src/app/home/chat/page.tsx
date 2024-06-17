import Box from "@mui/material/Box";
import StartConversation from "@/components/start-conversation";
import {SideBar} from "@/components/side-bar";
import {SelectedRoomContextProvider} from "@/context/selected-room-context";
import {Item} from "@/components/item-list";
import {getConversationList} from "@/server/chatting-data";
import {getServerSession} from "next-auth/next";
import {authOption} from "@/configs/next-auth-config";
import {Session} from "next-auth";
import ChattingBox from "@/components/chatting-box";
import Divider from "@mui/material/Divider";

export default async function ChatPage(){
    const chats:Item[] = await getConversationList()
    const session = await getServerSession(authOption as any) as Session;

    return(
        <Box sx={{display:'flex',height:'100%'}} >
            <SelectedRoomContextProvider>
                <SideBar chats={chats}/>
                <Divider orientation="vertical"/>

                {/*<StartConversation onClickStart={() =>{}}/>*/}
                <ChattingBox senderUserId={session.id} conversationId={'f52cd920-df5e-4997-927c-41fc572654f5'}/>
            </SelectedRoomContextProvider>
        </Box>
    )
}