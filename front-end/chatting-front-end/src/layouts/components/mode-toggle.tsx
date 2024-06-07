// ** MUI Imports
'use client'
import { PaletteMode } from '@mui/material'
import IconButton from '@mui/material/IconButton'

// ** Icons Imports
import WeatherNight from 'mdi-material-ui/WeatherNight'
import WeatherSunny from 'mdi-material-ui/WeatherSunny'
// ** Type Import
import {useSettings} from "@/hooks/useSettings";



const ModeToggle = () => {
    // ** Props
    const {settings, saveSettings} = useSettings()

    const handleModeChange = (mode: PaletteMode) => {
        saveSettings({ ...settings, mode })
    }

    const handleModeToggle = () => {
        if (settings.mode === 'light') {
            handleModeChange('dark')
        } else {
            handleModeChange('light')
        }
    }

    return (
        <IconButton color='inherit' aria-haspopup='true' onClick={handleModeToggle}>
            {settings.mode === 'dark' ? <WeatherSunny /> : <WeatherNight />}
        </IconButton>
    )
}

export default ModeToggle
