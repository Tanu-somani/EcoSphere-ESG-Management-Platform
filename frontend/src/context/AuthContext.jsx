import { createContext, useContext, useEffect, useMemo, useState } from 'react';
import apiClient from '../services/apiClient';

const AuthContext = createContext(null);

export function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const storedUser = localStorage.getItem('ecosphere-user');
    const token = localStorage.getItem('ecosphere-token');

    if (storedUser && token) {
      try {
        setUser(JSON.parse(storedUser));
      } catch {
        localStorage.removeItem('ecosphere-user');
        localStorage.removeItem('ecosphere-token');
      }
    }

    setLoading(false);
  }, []);

  const login = async (payload) => {
    const response = await apiClient.post('/auth/login', payload);
    const token = response.data?.token || response.data?.accessToken || response.data?.jwt;
    const currentUser = response.data?.user || response.data;

    if (token) {
      localStorage.setItem('ecosphere-token', token);
    }
    if (currentUser) {
      localStorage.setItem('ecosphere-user', JSON.stringify(currentUser));
      setUser(currentUser);
    }

    return response.data;
  };

  const signup = async (payload) => {
    const response = await apiClient.post('/auth/register', payload);
    return response.data;
  };

  const logout = () => {
    localStorage.removeItem('ecosphere-token');
    localStorage.removeItem('ecosphere-user');
    setUser(null);
  };

  const value = useMemo(() => ({ user, loading, login, signup, logout }), [user, loading]);

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export function useAuth() {
  return useContext(AuthContext);
}
