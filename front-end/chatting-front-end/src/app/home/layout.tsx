import {ReactNode, Suspense} from "react";
import {getServerSession} from "next-auth/next";
import {redirect} from "next/navigation";
import {Session} from "next-auth";
import {authOption} from "@/configs/next-auth-config";
import Box from "@mui/material/Box";
import {TopNav} from "@/layouts/top-nav";
import ContentWrapper from "@/layouts/components/content-wrapper";
import {Card} from "@mui/material";
import {getUserName} from "@/server/chatting-data";

export default async function HomeLayOut({children}: Readonly<{ children: ReactNode; }>) {
    const session = await getServerSession(authOption as any) as Session;
    if(!session || !session.access_token) {
        redirect("/auth/login");
    }
    if (session.admin) {
        redirect("/admin");
    }
    const name = await getUserName()
    return(
        <Box sx={{p:'10px'}}>
            <TopNav name={name}/>
            <ContentWrapper
                className='layout-page-content'
            >
                <Card sx={{height:"91vh"}}>
                    <Suspense>
                        {children}
                    </Suspense>
                </Card>
            </ContentWrapper>
        </Box>
    )
}