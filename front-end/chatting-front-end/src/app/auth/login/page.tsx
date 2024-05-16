'use client'
import Box from '@mui/material/Box'
import {
  Button,
  Checkbox,
  FormControl,
  IconButton,
  InputAdornment, Link,
  TextField,
  Typography
} from "@mui/material";
import {useRouter} from "next/navigation";
import {ChangeEvent, useState} from "react";
import {EyeOffOutline, EyeOutline} from "mdi-material-ui";

export interface State {
  password: string
  showPassword: boolean
}


const LoginPage = () => {
  // ** State
  const [values, setValues] = useState<State>({
    password: '',
    showPassword: false
  })

  // ** Hook
  const router = useRouter()

  const handleChange = (prop: keyof State) => (event: ChangeEvent<HTMLInputElement>) => {
    setValues({ ...values, [prop]: event.target.value })
  }

  const handleClickShowPassword = () => {
    setValues({ ...values, showPassword: !values.showPassword })
  }

  return (
      <Box >
          <Box sx={{mb:6}}>
            <Typography variant='body2'>Please sign-in to your account and start the adventure</Typography>
          </Box>

          <TextField autoFocus fullWidth id='email' label='Email' sx={{marginBottom: 4}}/>
          <TextField fullWidth label='Password' value={values.password} id='auth-register-password'
                     onChange={handleChange('password')}
                     type={values.showPassword ? 'text' : 'password'}
                     InputProps={{
                       endAdornment:
                           <InputAdornment position='end'>
                             <IconButton
                                 edge='end'
                                 onClick={handleClickShowPassword}
                                 aria-label='toggle password visibility'
                             >
                               {values.showPassword ? <EyeOutline fontSize='small'/> :
                                   <EyeOffOutline fontSize='small'/>}
                             </IconButton>
                           </InputAdornment>
                     }}
          />

          <Box
              sx={{mb: 4, display: 'flex', alignItems: 'center', flexWrap: 'wrap', justifyContent: 'space-between'}}
          >
            <Box>
              <Checkbox/>Remember Me
            </Box>
              <Link passHref href='/'>
                  Forgot Password?
              </Link>
          </Box>
          <Button
              fullWidth
              size='large'
              variant='contained'
              sx={{marginBottom: 7}}
              onClick={() => router.push('/')}
          >
            Login
          </Button>
          <Box sx={{display: 'flex', alignItems: 'center', flexWrap: 'wrap', justifyContent: 'center'}}>
            <Typography variant='body2' sx={{marginRight: 2}}>
              New on our platform?
            </Typography>
            <Typography variant='body2'>
              <Link passHref href='/auth/register'>
                Create an account
              </Link>
            </Typography>
          </Box>
      </Box>

  )
}


export default LoginPage
