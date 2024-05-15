'use client'
// ** React Imports
import React, {createContext, useState, ReactNode, useContext} from 'react'

// ** MUI Imports
import { PaletteMode } from '@mui/material'

// ** ThemeConfig Import
import themeConfig from "@/configs/themeConfig";
// ** Types Import
import {ThemeColor,ContentWidth} from "@/layouts/types";
import ThemeComponent from "@/theme/ThemeComponent";
export type Settings = {
  mode: PaletteMode
  themeColor: ThemeColor
  contentWidth: ContentWidth
}

export type SettingsContextValue = {
  settings: Settings
  saveSettings: (updatedSettings: Settings) => void
}

const initialSettings: Settings = {
  themeColor: 'primary',
  mode: themeConfig.mode,
  contentWidth: themeConfig.contentWidth
}

// ** Create Context
export const SettingsContext = createContext<SettingsContextValue>({
  saveSettings: () => null,
  settings: initialSettings
})

export const SettingsProvider = ({ children }: { children: ReactNode }) => {
  // ** State
  const [settings, setSettings] = useState<Settings>({ ...initialSettings })

  const saveSettings = (updatedSettings: Settings) => {
    setSettings(updatedSettings)
  }

  return <SettingsContext.Provider value={{ settings, saveSettings }}>{children}</SettingsContext.Provider>
}

export const SettingsConsumer = ({children,}:Readonly<{children:React.ReactNode}>) =>{
  const settings = useContext(SettingsContext).settings;
  return <ThemeComponent settings={settings}>
    {children}
  </ThemeComponent>
}
