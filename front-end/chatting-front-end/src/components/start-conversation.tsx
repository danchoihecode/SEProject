import Box from "@mui/material/Box";
import {Button, Card} from "@mui/material";
import ForumOutlinedIcon from '@mui/icons-material/ForumOutlined';
interface Props {
    onClickStart: () => void;
}

export default function StartConversation(props:Props) {

    return <Box sx={{ width:1, bgcolor: 'background.default', height:'100%',display:'flex', alignItems: 'center', justifyContent: 'center',flexDirection:'column'}}>
        <Card sx={{width: '150px' , height:'150px', borderRadius:'100px',display:'flex',alignItems:'center', justifyContent: 'center'}}>
            <ForumOutlinedIcon sx={{width:'75px',height:'75px'}}/>
        </Card>
        <Button variant='contained' sx={{mt:'20px',borderRadius:'25px'}}>
            Start Conversation
        </Button>
    </Box>
}