import Box from "@mui/material/Box";
import {Friend} from "@/components/side-bar";
import { getUserName} from "@/server/chatting-data";
import {listFriends} from "@/server/add-group";
import { Grid, Typography } from "@mui/material";
import FriendArea from "@/components/friend-area";
import PostArea  from "@/components/post-area";
import AccountAvatar from "@/components/letter-avatara";

export default async function PostPage(){
    const name = await getUserName();
    const friends: Friend[] = await listFriends();

    return(
        <Box>
            <Box
                display="flex"
                flexDirection="column"
                alignItems="center"
                justifyContent="center"
                p={2.5}
                height={200}
                sx={{
                    backgroundImage: `url('/bird.png')`,
                    backgroundSize: 'cover',
                    backgroundPosition: 'center'
                }}
            >
                <AccountAvatar name={name} />
            </Box>
            <Box mt={2} display="flex" justifyContent="center" p={2.5}>
                <Typography variant="h4" color="black">
                    {name}
                </Typography>
            </Box>
            <Grid container spacing={2}>
                <Grid item xs={8}>
                    <Box p={2.5}>
                        <PostArea />
                    </Box>
                </Grid>
                <Grid item xs={4}>
                    <Box p={2.5}>
                        <FriendArea friends={friends} />
                    </Box>
                </Grid>
            </Grid>
        </Box>
    )
}