'use server'
import { getServerSession } from "next-auth/next";
import { authOption } from "@/configs/next-auth-config";
import { Session } from "next-auth";
export const removeMember = async (memberId: string, conversationId: string) => {
    const session = await getServerSession(authOption as any) as Session;
    const requestBody = {
        memberId: memberId,
        conversationId: conversationId
    };

    try {
        const response = await fetch('http://localhost:8080/remove-member', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${session.access_token}`
            },
            body: JSON.stringify(requestBody)
        });

        if (!response.ok) {
            throw new Error(`Failed to remove member. Status: ${response.status}`);
        }

    } catch (error) {
        throw error;
    }
};