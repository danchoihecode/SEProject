import Box from "@mui/material/Box";
import ModeToggle from "@/layouts/components/mode-toggle";
import UserDropdown from "@/layouts/components/user-dropdown";
import Image from "next/image";
import themeConfig from "@/configs/themeConfig";
import {Typography} from "@mui/material";
import NotificationButton, {FriendRequest} from "@/components/notification-button";

interface Props{
    name:string
}
export const TopNav = async (props:Props) => {
    // API to addFriend when accept

    const data: FriendRequest[] = [];

    return(
        <Box sx={{ width: '100%', display: 'flex', alignItems: 'center', justifyContent: 'space-between'}}>
            <Box className='actions-left' sx={{ mr: 2, display: 'flex', alignItems: 'center' }}>
                <a href={"/home/chat"} style={{display: 'flex',
                    alignItems: 'center',
                    textDecoration: 'none'}}>
                    <Image src="/logo.ico" width={40} height={40} alt={"logo"}/>
                    <Typography variant='h6' sx={{
                        ml: 3,
                        fontWeight: 600,
                        lineHeight: 'normal',
                        textTransform: 'uppercase'
                    }}>
                        {themeConfig.templateName}
                    </Typography>
                </a>
            </Box>

            <Box className='actions-right' sx={{ display: 'flex', alignItems: 'center' }}>
                <NotificationButton friendRequests={data}/>
                <ModeToggle/>
                <UserDropdown name={props.name} />
            </Box>
        </Box>
    )
}