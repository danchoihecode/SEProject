'use client'
import {styled} from "@mui/material/styles";

const ContentWrapper = styled('main')(({ theme }) => ({
    flexGrow: 1,
    width: '100%',
    padding: theme.spacing(2),
    transition: 'padding .25s ease-in-out',
    [theme.breakpoints.down('sm')]: {
        paddingLeft: theme.spacing(1),
        paddingRight: theme.spacing(1)
    }
}))

export default ContentWrapper