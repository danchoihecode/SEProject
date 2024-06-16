import {getServerSession} from "next-auth/next";
import {authOption} from "@/configs/next-auth-config";
import {Session} from "next-auth";
import ChattingBox from "@/components/chatting-box";

export default async function TestPage() {
    const session = await getServerSession(authOption as any) as Session;

    return <ChattingBox senderUserId={session.id} conversationId={'f52cd920-df5e-4997-927c-41fc572654f5'}/>
}