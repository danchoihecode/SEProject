'use client'
import Box from '@mui/material/Box'
import {
    Button,
    Checkbox,
    IconButton,
    InputAdornment, Link, Stack,
    TextField,
    Typography
} from "@mui/material";
import {useRouter} from "next/navigation";
import {useState} from "react";
import {EyeOffOutline, EyeOutline} from "mdi-material-ui";
import { signIn} from "next-auth/react";
import {useFormik} from "formik";
import * as Yup from "yup";


const LoginPage = () => {
    // ** State
    const [showPassword, setShowPassword] = useState(false)
    // ** Hook
    const router = useRouter()

    const formik = useFormik({
        initialValues: {
            email:'',
            password: '',
            submit: null
        },
        validationSchema: Yup.object({
            email: Yup
                .string()
                .max(100)
                .email('Must be a valid email')
                .required('Email is required'),
            password: Yup
                .string()
                .max(100)
                .required('Password is required')
        }),
        onSubmit: async (values, helpers) => {
            const response = await signIn('login', {
                redirect: false,
                email: values.email,
                password: values.password
            }) as ResponseData
    
            if (!response.error){
                router.push('/home');
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
              <Typography variant='body2'>Please sign-in to your account and start the adventure</Typography>
          </Box>

          <form noValidate onSubmit={formik.handleSubmit}>
              <Stack spacing={3} sx={{minWidth: '55vh'}}>
                  {formik.errors.submit && (
                      <Typography
                          color="error"
                          sx={{ mt: 3 }}
                          variant="body2"
                      >
                          {formik.errors.submit}
                      </Typography>
                  )}
                  <TextField fullWidth label='Email' sx={{marginBottom: 4}}
                             name='email'
                             error={!!(formik.touched.email && formik.errors.email)}
                             helperText={formik.touched.email && formik.errors.email}
                             onBlur={formik.handleBlur}
                             value={formik.values.email}
                             onChange={formik.handleChange}/>
                  <TextField fullWidth label='Password'
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
                             name='password'
                             error={!!(formik.touched.password && formik.errors.password)}
                             helperText={formik.touched.password && formik.errors.password}
                             onBlur={formik.handleBlur}
                             value={formik.values.password}
                             onChange={formik.handleChange}
                  />
              </Stack>

              <Box
                  sx={{
                      mb: 4,
                      display: 'flex',
                      alignItems: 'center',
                      flexWrap: 'wrap',
                      justifyContent: 'space-between'
                  }}
              >
                  <Box>
                      <Checkbox/>Remember Me
                  </Box>
                  <Link href='/'>
                      Forgot Password?
                  </Link>
              </Box>
              <Button
                  fullWidth
                  size='large'
                  variant='contained'
                  sx={{marginBottom: 7}}
                  type='submit'
              >
                  Login
              </Button>

              <Box sx={{display: 'flex', alignItems: 'center', flexWrap: 'wrap', justifyContent: 'center'}}>
                  <Typography variant='body2' sx={{marginRight: 2}}>
                      New on our platform?
                  </Typography>
                  <Typography variant='body2'>
                      <Link href='/auth/register'>
                          Create an account
                      </Link>
                  </Typography>
              </Box>
          </form>
      </Box>

)
}


export default LoginPage
