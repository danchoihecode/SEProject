declare module  '@mui/material/styles' {
  export interface Palette{
    customColors: {
      main: string
      tableHeaderBg: string
      primaryGradient: string
    }
  }
  export interface PaletteOptions {
    customColors?: {
      main?: string
      tableHeaderBg?: string
      primaryGradient?: string
    }
  }
}

