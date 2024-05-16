import {Palette as MuiPalette, PaletteOptions as MuiPaletteOptions} from '@mui/material/styles'
declare module  '@mui/material/styles' {
  export interface Palette extends MuiPalette{
    customColors: {
      main: string
      tableHeaderBg: string
      primaryGradient: string
    }
  }
  export interface PaletteOptions extends MuiPaletteOptions{
    customColors?: {
      main?: string
      tableHeaderBg?: string
      primaryGradient?: string
    }
  }
}

