import {ReactNode} from "react";
import Box from "@mui/material/Box";
import BlankLayoutWrapper from "@/layouts/BlankLayoutWrapper";
import {Card, CardContent, Typography} from "@mui/material";
import themeConfig from "@/configs/themeConfig";
import Image from "next/image"

const AuthLayout = ({ children }: Readonly<{children: ReactNode}>) => {
    return (
        <BlankLayoutWrapper className='layout-wrapper'>
            <Box className='app-content' sx={{ minHeight: '100vh', overflowX: 'hidden', position: 'relative' }}>
                <Box className='content-center'>
                    <Card sx={{ zIndex: 1,maxWidth:'55vh'}}>
                        <CardContent>
                            <Box sx={{ mb: 8, display: 'flex', alignItems: 'center', justifyContent: 'center' }}>
                                <Image
                                    src="/logo.ico"
                                    width={40}
                                    height={40}
                                    alt={"logo here!"}/>
                                <Typography
                                    variant='h6'
                                    sx={{
                                        ml: 3,
                                        lineHeight: 1,
                                        fontWeight: 600,
                                        textTransform: 'uppercase',
                                        fontSize: '1.5rem !important'
                                    }}
                                >
                                    {themeConfig.templateName}
                                </Typography>
                            </Box>
                            <Typography variant='h5' sx={{ fontWeight: 600, marginBottom: 1.5 }}>
                                Welcome to our community! 👋🏻
                            </Typography>
                            {children}
                        </CardContent>
                    </Card>
                </Box>
            </Box>
        </BlankLayoutWrapper>
    )
}

export default AuthLayout