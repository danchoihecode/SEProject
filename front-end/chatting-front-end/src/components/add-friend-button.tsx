'use client'
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import {Item} from "@/components/item-list";
interface buttonInfo{
    isFriend :boolean;
    isAdded :boolean;
    onAddFriend: () => void;
}
const AddFriendButton = (props : buttonInfo ) => {
    const { onAddFriend ,isFriend, isAdded } = props;

    const handleClick = () => {
        onAddFriend();
    };

    if (isFriend) {
        return null; // Don't render the component if isFriend is true
    }

    return (
        <PersonAddIcon
            onClick={handleClick}
            sx={{
                opacity: isAdded ? 0.5 : 1, // Fade effect when isAdded is true
                cursor: isAdded ? 'not-allowed' : 'pointer', // Disable cursor if isAdded is true
            }}
        />
    );
}

export default AddFriendButton;