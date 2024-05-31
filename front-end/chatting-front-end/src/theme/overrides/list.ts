// ** MUI Imports
import { Theme } from '@mui/material/styles'

const List = (theme: Theme) => {
  return {
    MuiListItemIcon: {
      styleOverrides: {
        root: {
          minWidth: 0,
          marginRight: theme.spacing(2.25),
          color: theme.palette.text.secondary
        }
      }
    },
    MuiListItemAvatar: {
      styleOverrides: {
        root: {
          minWidth: 0,
          marginRight: theme.spacing(4)
        }
      }
    },
    MuiListItemText: {
      styleOverrides: {
        dense: {
          '& .MuiListItemText-primary': {
            color: theme.palette.text.primary
          }
        }
      }
    },
    MuiListSubheader: {
      styleOverrides: {
        root: {
          fontWeight: 600,
          textTransform: 'uppercase',
          color: theme.palette.text.primary
        }
      }
    },
    MuiListItemButton:{
      styleOverrides: {
        root:{
          '&.Mui-selected': {
            backgroundColor: theme.palette.action.selected, // Custom background color for selected item
            '&:hover': {
              backgroundColor: theme.palette.action.hover, // Custom hover color for selected item
            },
          },
        }
      }
    }
  }
}

export default List
