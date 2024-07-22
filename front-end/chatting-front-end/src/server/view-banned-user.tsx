'use server'
import { getServerSession } from "next-auth/next";
import { authOption } from "@/configs/next-auth-config";
import { Session } from "next-auth";
import { UserDTO } from "@/dto/PostDTO";

export const getBannedUsers = async (): Promise<UserDTO[]> => {
    const session = await getServerSession(authOption as any) as Session;

    try {
        const response = await fetch('http://localhost:8080/banned_list', {
            method: 'GET',
            headers: {
                'Content-Type': 'application/json',
                Authorization: `Bearer ${session.access_token}`
            }
        });

        if (!response.ok) {
            throw new Error(`Failed to fetch users. Status: ${response.status}`);
        }

        const text = await response.text();
        const data: UserDTO[] = text ? JSON.parse(text) : [];

        return data;
    } catch (error) {
        throw error;
    }
};
