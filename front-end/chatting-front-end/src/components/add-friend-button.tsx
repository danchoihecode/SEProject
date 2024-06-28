'use client'
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import {useState} from "react";
import {IconButton} from "@mui/material";
import {handleAddFriend} from "@/server/friend-request";

interface buttonInfo{
    isFriend :boolean;
    id:string
}
const AddFriendButton = (props : buttonInfo ) => {
    const {isFriend } = props;
    const [flag, setFlag] = useState(isFriend);

    const handleClick = () => {
        handleAddFriend(props.id).then(
            r => {
                if(r) setFlag(true);
            }
    )
    };

    if (isFriend) {
        return null; // Don't render the component if isFriend is true
    }

    return (
        <IconButton>
            <PersonAddIcon
                onClick={handleClick}
                sx={{
                    display: flag ? 'none':'flex',
                }}
            />
        </IconButton>

    );
}

export default AddFriendButton;