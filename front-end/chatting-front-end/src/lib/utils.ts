import { type ClassValue, clsx } from "clsx";
import { twMerge } from "tailwind-merge";
import { cookies } from "next/headers";

export function cn(...inputs: ClassValue[]) {
    return twMerge(clsx(inputs));
}
export const capitalizeFirstLetter = (path: string) => {
    return path
        .split("-")
        .map((word) => word.charAt(0).toUpperCase() + word.slice(1))
        .join(" ");
};
export function isWithinNextWeek(date: Date): boolean {
    const today = new Date();
    const nextWeek = new Date();
    nextWeek.setDate(today.getDate() + 7);

    return date >= today && date <= nextWeek;
}

