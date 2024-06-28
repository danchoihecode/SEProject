'use client'
import Box from "@mui/material/Box";
import ModeToggle from "@/layouts/components/mode-toggle";
import UserDropdown from "@/layouts/components/user-dropdown";
import Image from "next/image";
import themeConfig from "@/configs/themeConfig";
import {Typography} from "@mui/material";
import NotificationButton from "@/components/notification-button";

interface Props{
    name:string
}
export const TopNav = (props:Props) => {
    // API to addFriend when accept

    const handleAccept = (id: string) => {
        console.log('Accepted friend request with ID:', id);
    };

    const handleReject = (id: string) => {
        console.log('Rejected friend request with ID:', id);
    };
    const friendRequests = [
        { name: 'John Doe', id: '1' },
        { name: 'Jane Smith', id: '2' },
        { name: 'Bob Johnson', id: '3' },
    ];
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
                <NotificationButton
                    friendRequests={friendRequests}
                    onAccept={handleAccept}
                    onReject={handleReject}
                />
                <ModeToggle/>
                <UserDropdown name={props.name} />
            </Box>
        </Box>
    )
}