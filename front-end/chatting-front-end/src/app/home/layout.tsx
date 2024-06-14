import {ReactNode} from "react";
import {getServerSession} from "next-auth/next";
import {redirect} from "next/navigation";
import {Session} from "next-auth";
import {authOption} from "@/configs/next-auth-config";
import Box from "@mui/material/Box";
import {TopNav} from "@/layouts/top-nav";
import ContentWrapper from "@/layouts/components/content-wrapper";
import {Card} from "@mui/material";

export default async function HomeLayOut({children}: Readonly<{ children: ReactNode; }>) {
    const session = await getServerSession(authOption as any) as Session;
    if(!session || !session.access_token) {
        redirect("/auth/login");
    }
    return(
        <Box sx={{p:'10px'}}>
            <TopNav/>
            <ContentWrapper
                className='layout-page-content'
            >
                <Card fullwidth sx={{height:"91vh"}}>
                    {children}
                </Card>
            </ContentWrapper>
        </Box>
    )
}