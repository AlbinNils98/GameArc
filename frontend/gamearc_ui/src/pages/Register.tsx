import { ChangeEvent, FormEvent, useState } from 'react';
import { IoLockClosedOutline, IoPersonOutline } from 'react-icons/io5';
import api from '../api/axios';

export default function Register() {

      const [username, setUserName] = useState('');
      const [password, setPassword] = useState('');
      const [confirmPassword, setConfirmPassword] = useState('');
  
      const register = async (e: FormEvent) => {
          e.preventDefault();

          if(password !== confirmPassword){
            console.log('Passwords not matching');
            return;
          } 
  
          try{
              await api.post('/register', 
              {username,password}, 
              {withCredentials: true})
          }catch(error: unknown){
              console.log(error)
          }
             
      }
  
      const handleUsername = (event: ChangeEvent<HTMLInputElement>) => {
          setUserName(event.target.value);
      }
  
      const handlePassword = (event: ChangeEvent<HTMLInputElement>) => {
          setPassword(event.target.value);
      }
      const handleConfirmPassword = (event: ChangeEvent<HTMLInputElement>) => {
        setConfirmPassword(event.target.value);
      }
  return (
    <div className='flex justify-center items-center h-screen'>
    <section className='credentialBox'>
    <form onSubmit={register}>
        <h1>Sign Up</h1>
        <div className="inputbox">
            <IoPersonOutline className='ion-icon' />
            <input 
            value={username} 
            onChange={handleUsername} 
            type="text" 
            id= "username" 
            name="username" 
            required />
            <label >Username</label>
        </div>
        <div className="inputbox">
            <IoLockClosedOutline className='ion-icon' />
            <input 
            value={password}
            onChange={handlePassword}
            type="password" 
            id="password" 
            name="password" 
            required />
            <label>Password</label>
        </div>
        <div className="inputbox">
            <IoLockClosedOutline className='ion-icon' />
            <input
            value={confirmPassword}
            onChange={handleConfirmPassword} 
            type="password" 
            id="passwordcon" 
            name="confirm-password" 
            required />
            <label>Confirm Password</label>
        </div>
        <button 
        id= "submit" 
        type="submit"
        >Sign Up</button>
        <div className="register">
            <p>Already have an account? <a href="/login">Log In</a></p>
        </div>
    </form>
</section>
    </div>
  )
}