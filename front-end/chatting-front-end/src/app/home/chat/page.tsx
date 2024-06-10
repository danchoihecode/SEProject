import Box from "@mui/material/Box";
import StartConversation from "@/components/start-conversation";
import {SideBar} from "@/components/side-bar";
import {SelectedRoomContextProvider} from "@/context/selected-room-context";

export default function ChatPage(){



    return(
        <Box sx={{display:'flex',height:'100%'}} >
            <SelectedRoomContextProvider>
                <SideBar/>
                <StartConversation onClickStart={() =>{}}/>
            </SelectedRoomContextProvider>
        </Box>
    )
}