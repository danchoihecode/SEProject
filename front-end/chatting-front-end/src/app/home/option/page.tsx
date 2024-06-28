import Box from "@mui/material/Box";
import Divider from "@mui/material/Divider";
import { SideBar } from "@/components/side-bar";
import { SelectedRoomContextProvider } from "@/context/selected-room-context";
import { Item } from "@/components/item-list";
import { getConversationList} from "@/server/chatting-data";
import OptionList from "@/components/option-list"; 
import {getServerSession} from "next-auth/next";
import {authOption} from "@/configs/next-auth-config";
import {Session} from "next-auth";

export default async function OptionPage() {
    const chats: Item[] = await getConversationList();
    const session = await getServerSession(authOption as any) as Session;

    return (
        <Box sx={{ display: 'flex', height: '100%' }}>
            <SelectedRoomContextProvider>
                <SideBar chats={chats} />
                <Divider orientation="vertical" />
                <OptionList userId ={session.id} />
            </SelectedRoomContextProvider>
        </Box>
    );
}
