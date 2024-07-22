'use client';
import { Button } from '@/components/ui/button';
import {
    DropdownMenu,
    DropdownMenuContent,
    DropdownMenuGroup,
    DropdownMenuItem,
    DropdownMenuLabel,
    DropdownMenuSeparator,
    DropdownMenuShortcut,
    DropdownMenuTrigger
} from '@/components/ui/dropdown-menu';
import { signOut } from 'next-auth/react';
import { useRouter } from 'next/navigation';
import { Icons } from '@/components/icons';

export function UserNav() {
    const router = useRouter();

    const handleChangePasswordClick = () => {
        router.push('/auth/change-password');
    };

    const handleDropdownClose = (url?: string) => {
        localStorage.clear();
        if (url) {
            signOut({redirect:false})
            router.push(url)
            router.refresh()
        }
    }

    const Icon = Icons['profile'];

    return (
        <DropdownMenu>
            <DropdownMenuTrigger asChild>
                <Button variant="outline" size="icon" className="overflow-hidden rounded-full">
                    <Icon className="size-5" />
                </Button>
            </DropdownMenuTrigger>
            <DropdownMenuContent className="w-56" align="end" forceMount>
                <DropdownMenuLabel className="font-normal">
                    <div className="flex flex-col space-y-1">
                        <p className="text-sm font-medium leading-none">
                            Settings
                        </p>
                        <p className="text-xs leading-none text-muted-foreground">
                            { }
                        </p>
                    </div>
                </DropdownMenuLabel>
                <DropdownMenuSeparator />
                <DropdownMenuGroup>
                    <DropdownMenuItem onClick={handleChangePasswordClick}>
                        Change Password
                        <DropdownMenuShortcut>⇧⌘W</DropdownMenuShortcut>
                    </DropdownMenuItem>
                </DropdownMenuGroup>
                <DropdownMenuSeparator />
                <DropdownMenuItem onClick={() => handleDropdownClose('/auth/login') } >
                    Log out
                    <DropdownMenuShortcut>⇧⌘Q</DropdownMenuShortcut>
                </DropdownMenuItem>
            </DropdownMenuContent>
        </DropdownMenu>
    );
}
