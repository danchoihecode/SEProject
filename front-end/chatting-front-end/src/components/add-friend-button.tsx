'use client'
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import {Item} from "@/components/item-list";
import {useState} from "react";
import {IconButton} from "@mui/material";
interface buttonInfo{
    isFriend :boolean;
}
const AddFriendButton = (props : buttonInfo ) => {
    const {isFriend } = props;
    const [flag, setFlag] = useState(isFriend);

    const handleClick = () => {
        console.log(isFriend);
        setFlag(true);
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