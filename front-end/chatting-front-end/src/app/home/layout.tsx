import {ReactNode} from "react";
import {getServerSession} from "next-auth/next";
import {redirect} from "next/navigation";
import {Session} from "next-auth";
import {authOption} from "@/configs/next-auth-config";


export default async function HomeLayOut({children}: Readonly<{ children: ReactNode; }>) {
    const session = await getServerSession(authOption as any) as Session;
    if(!session || !session.access_token) {
        redirect("/auth/login");
    }
    return children
}