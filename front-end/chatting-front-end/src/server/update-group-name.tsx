'use server'
import {getServerSession} from "next-auth/next";
import {authOption} from "@/configs/next-auth-config";
import {Session} from "next-auth";

export const updateGroupName = async (selectedIndex: string, newGroupName: string) => {
    const session = await getServerSession(authOption as any) as Session;
    const requestBody = {
        conversationId: selectedIndex,
        newGroupName: newGroupName
    };

    try {
        const response = await fetch('http://localhost:8080/update-group-name', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${session.access_token}`
            },
            body: JSON.stringify(requestBody)
        });

        if (!response.ok) {
            throw new Error(`Failed to update group name. Status: ${response.status}`);
        }

    } catch (error) {
        throw error;
    }
};
