'use server'
import { getServerSession } from "next-auth/next";
import { authOption } from "@/configs/next-auth-config";
import { Session } from "next-auth";

export const removePost = async (reportId: string) => {
    const session = await getServerSession(authOption as any) as Session;

    try {
        const response = await fetch(`http://localhost:8080/admin/declineReport/${reportId}`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${session.access_token}`
            }
        });

        if (!response.ok) {
            throw new Error(`Failed to decline report. Status: ${response.status}`);
        }

    } catch (error) {
        throw error;
    }
};
