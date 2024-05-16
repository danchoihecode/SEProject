'use client'
// ** React Imports
import { useState, ChangeEvent} from 'react'
// ** MUI Components
import Box from '@mui/material/Box'
import {
    Button,
    Checkbox,
    IconButton,
    InputAdornment,
     Link,
    OutlinedInput,
    TextField,
    Typography
} from "@mui/material";
import {useRouter} from "next/navigation";

import {State} from "@/app/auth/login/page";
import {EyeOffOutline, EyeOutline} from "mdi-material-ui";

const RegisterPage = () => {
  // ** States
  const [values, setValues] = useState<State>({
    password: '',
    showPassword: false
  })

    const router = useRouter()
  const handleChange = (prop: keyof State) => (event: ChangeEvent<HTMLInputElement>) => {
    setValues({ ...values, [prop]: event.target.value })
  }
  const handleClickShowPassword = () => {
    setValues({ ...values, showPassword: !values.showPassword })
  }


  return (
      <Box>
          <Box sx={{mb: 6}}>
              <Typography variant='body2'>Make an account here!</Typography>
          </Box>
          <TextField autoFocus fullWidth type='text' id='username' label='Username' sx={{marginBottom: 4}}/>
          <TextField fullWidth type='email' label='Email' sx={{marginBottom: 4}}/>
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
                  <Checkbox/>
                  <span>I agree to </span>
                  <Link href='/' passHref>
                      privacy policy & terms
                  </Link>
              </Box>
          </Box>

          <Button fullWidth size='large' type='submit' variant='contained' sx={{marginBottom: 7}}>
              Sign up
          </Button>
          <Box sx={{display: 'flex', alignItems: 'center', flexWrap: 'wrap', justifyContent: 'center'}}>
              <Typography variant='body2' sx={{marginRight: 2}}>
                  Already have an account?
              </Typography>
              <Typography variant='body2'>
                  <Link passHref href='/auth/login'>
                      Sign in instead
                  </Link>
              </Typography>
          </Box>
      </Box>

  )
}

export default RegisterPage
