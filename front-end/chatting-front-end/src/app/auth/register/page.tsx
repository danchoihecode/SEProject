'use client'
// ** React Imports
import { useState} from 'react'
// ** MUI Components
import Box from '@mui/material/Box'
import {
    Button,
    Checkbox,
    IconButton,
    InputAdornment,
    Link, Stack,
    TextField,
    Typography
} from "@mui/material";
import {useRouter} from "next/navigation";
import {useFormik} from "formik";
import * as Yup from 'yup';
import {EyeOffOutline, EyeOutline} from "mdi-material-ui";
import {signIn} from "next-auth/react";

const RegisterPage = () => {

    const [showPassword,setShowPassword] = useState(false);
    const [policy, setPolicy] = useState(true);
    const router = useRouter()

    const formik = useFormik({
        initialValues: {
            email:'',
            fullName:'',
            password: '',
            repassword:'',
            submit: null
        },
        validationSchema: Yup.object({
            email: Yup
                .string()
                .max(100)
                .email('Must be a valid email')
                .required('Email is required'),
            fullName: Yup
                .string()
                .max(100)
                .required('FullName is required'),
            password: Yup
                .string()
                .max(100)
                .required('Password is required'),
            repassword:  Yup.string()
                .oneOf([Yup.ref('password')], 'Passwords must match')
                .required('RePassword is required'),

        }),
        onSubmit: async (values, helpers) => {
            const response = await signIn('register', {
                redirect: false,
                email: values.email,
                fullName: values.fullName,
                password: values.password,
            }) as ResponseData

            if (!response.error){
                router.push('/auth/login');
            } else {
                helpers.setStatus({ success: false });
                helpers.setErrors({ submit: response.error });
                helpers.setSubmitting(false);
            }
        }
    });

  return (
      <Box>
          <Box sx={{mb: 6}}>
              <Typography variant='body2'>Make an account here!</Typography>
          </Box>
          <form noValidate onSubmit={formik.handleSubmit}>
              <Stack spacing={3} sx={{minWidth:'55vh'}}>
                  {formik.errors.submit && (
                      <Typography
                          color="error"
                          sx={{ mt: 3 }}
                          variant="body2"
                      >
                          {formik.errors.submit}
                      </Typography>
                  )}
                  <TextField
                      fullWidth
                      type='text'
                      label='FullName'
                      name='fullName'
                      sx={{marginBottom: 4}}
                      error={!!(formik.touched.fullName && formik.errors.fullName)}
                      helperText={formik.touched.fullName && formik.errors.fullName}
                      onBlur={formik.handleBlur}
                      value={formik.values.fullName}
                      onChange={formik.handleChange}
                  />
                  <TextField
                      fullWidth
                      type='email'
                      label='Email'
                      name='email'
                      sx={{marginBottom: 4}}
                      error={!!(formik.touched.email && formik.errors.email)}
                      helperText={formik.touched.email && formik.errors.email}
                      onBlur={formik.handleBlur}
                      value={formik.values.email}
                      onChange={formik.handleChange}
                  />
                  <TextField
                      fullWidth label='Password'
                      name='password'
                      type={showPassword ? 'text' : 'password'}
                      InputProps={{
                          endAdornment:
                             <InputAdornment position='end'>
                                 <IconButton
                                     edge='end'
                                     onClick={() =>  setShowPassword(!showPassword)}
                                     aria-label='toggle password visibility'
                                 >
                                     {showPassword ? <EyeOutline fontSize='small'/> :
                                         <EyeOffOutline fontSize='small'/>}
                                 </IconButton>
                             </InputAdornment>
                  }}
                      error={!!(formik.touched.password && formik.errors.password)}
                      helperText={formik.touched.password && formik.errors.password}
                      onBlur={formik.handleBlur}
                      value={formik.values.password}
                      onChange={formik.handleChange}
                  />
                  <TextField
                      fullWidth label='Retype Password'
                      name='repassword'
                      type={showPassword ? 'text' : 'password'}
                      InputProps={{
                          endAdornment:
                              <InputAdornment position='end'>
                                  <IconButton
                                      edge='end'
                                      onClick={() => setShowPassword(!showPassword)}
                                      aria-label='toggle password visibility'
                                  >
                                      {showPassword ? <EyeOutline fontSize='small'/> :
                                          <EyeOffOutline fontSize='small'/>}
                                  </IconButton>
                              </InputAdornment>
                      }}
                      error={!!(formik.touched.repassword && formik.errors.repassword)}
                      helperText={formik.touched.repassword && formik.errors.repassword}
                      onBlur={formik.handleBlur}
                      value={formik.values.repassword}
                      onChange={formik.handleChange}
                  />
              </Stack>

              <Box
                  sx={{mb: 4, display: 'flex', alignItems: 'center', flexWrap: 'wrap', justifyContent: 'space-between'}}
              >
                  <Box>
                      <Checkbox onChange={()=>{
                          setPolicy(!policy)
                      }}/>
                      <span>I agree to </span>
                      <Link href='/' >
                          privacy policy & terms
                      </Link>
                  </Box>
              </Box>

              <Button
                  fullWidth size='large'
                  disabled={policy}
                  type='submit'
                  variant='contained'
                  sx={{marginBottom: 7}}
              >
                  Sign up
              </Button>
              <Box sx={{display: 'flex', alignItems: 'center', flexWrap: 'wrap', justifyContent: 'center'}}>
                  <Typography variant='body2' sx={{marginRight: 2}}>
                      Already have an account?
                  </Typography>
                  <Typography variant='body2'>
                      <Link href='/auth/login'>
                          Sign in instead
                      </Link>
                  </Typography>
              </Box>
          </form>

      </Box>

  )
}

export default RegisterPage
