'use client'
import * as React from 'react';
import Avatar from '@mui/material/Avatar';
import router from 'next/router';

// use different background colors for the avatar, based on the name of person.
export function stringToColor(string: string) {
    let hash = 0;
    let i;

    /* eslint-disable no-bitwise */
    for (i = 0; i < string.length; i += 1) {
        hash = string.charCodeAt(i) + ((hash << 5) - hash);
    }

    let color = '#';

    for (i = 0; i < 3; i += 1) {
        const value = (hash >> (i * 8)) & 0xff;
        color += `00${value.toString(16)}`.slice(-2);
    }
    /* eslint-enable no-bitwise */

    return color;
}

export function stringAvatar(name: string) {
    return `${name.toUpperCase()[0]}`

}

interface Props{
    name:string ,
}
export  default function AccountAvatar(props:Props) {
    return (
        <Avatar onClick={() => { router.push(`/home/post/${props.name}`);  }}
                sx={{
                    bgcolor: stringToColor(props.name),
                    cursor: 'pointer',
                    height: '8rem',
                    width: '8rem',
                }}
        >
            {stringAvatar(props.name)}
        </Avatar>
    );

}

