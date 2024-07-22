'use client'
import { NextPage } from 'next';
import { usePathname, useSearchParams } from 'next/navigation';
import Box from "@mui/material/Box";
import { Grid, Typography } from "@mui/material";
import PostArea from "@/components/post-area";
import AccountAvatar from "@/components/letter-avatara";

const PostPage: NextPage = () => {
    const pathname = usePathname();
    const [, , , id] = pathname.split('/');

    return (
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
                {/* <AccountAvatar name={id || ''} /> */}
            </Box>
            <Box mt={2} display="flex" justifyContent="center" p={2.5}>
            </Box>

                <Box p={2.5}>
                    <PostArea name={id || ''} />
                </Box>

        </Box>
    );
};

export default PostPage;