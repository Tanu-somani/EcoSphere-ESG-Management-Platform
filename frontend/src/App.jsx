import { Navigate, Route, Routes } from 'react-router-dom';
import { AuthProvider, useAuth } from './context/AuthContext';
import AppShell from './components/layout/AppShell';
import LandingPage from './pages/LandingPage';
import LoginPage from './pages/LoginPage';
import SignupPage from './pages/SignupPage';
import DashboardPage from './pages/DashboardPage';
import EnvironmentalPage from './pages/EnvironmentalPage';
import SocialPage from './pages/SocialPage';
import GovernancePage from './pages/GovernancePage';
import ImpactHubPage from './pages/ImpactHubPage';
import ReportsPage from './pages/ReportsPage';
import SettingsPage from './pages/SettingsPage';
import ProfilePage from './pages/ProfilePage';
import NotFoundPage from './pages/NotFoundPage';

function ProtectedRoute({ children }) {
  const { user, loading } = useAuth();

  if (loading) {
    return <div className="loading-screen">Preparing EcoSphere...</div>;
  }

  return user ? children : <Navigate to="/login" replace />;
}

function AppRoutes() {
  const { user } = useAuth();

  return (
    <Routes>
      <Route path="/login" element={user ? <Navigate to="/preview" replace /> : <LoginPage />} />
      <Route path="/signup" element={user ? <Navigate to="/preview" replace /> : <SignupPage />} />
      <Route path="/" element={<Navigate to={user ? '/preview' : '/login'} replace />} />
      <Route
        path="/preview"
        element={
          <ProtectedRoute>
            <AppShell>
              <LandingPage />
            </AppShell>
          </ProtectedRoute>
        }
      />
      <Route
        path="/dashboard"
        element={
          <ProtectedRoute>
            <AppShell>
              <DashboardPage />
            </AppShell>
          </ProtectedRoute>
        }
      />
      <Route
        path="/environmental"
        element={
          <ProtectedRoute>
            <AppShell>
              <EnvironmentalPage />
            </AppShell>
          </ProtectedRoute>
        }
      />
      <Route
        path="/social"
        element={
          <ProtectedRoute>
            <AppShell>
              <SocialPage />
            </AppShell>
          </ProtectedRoute>
        }
      />
      <Route
        path="/governance"
        element={
          <ProtectedRoute>
            <AppShell>
              <GovernancePage />
            </AppShell>
          </ProtectedRoute>
        }
      />
      <Route
        path="/impact-hub"
        element={
          <ProtectedRoute>
            <AppShell>
              <ImpactHubPage />
            </AppShell>
          </ProtectedRoute>
        }
      />
      <Route
        path="/reports"
        element={
          <ProtectedRoute>
            <AppShell>
              <ReportsPage />
            </AppShell>
          </ProtectedRoute>
        }
      />
      <Route
        path="/settings"
        element={
          <ProtectedRoute>
            <AppShell>
              <SettingsPage />
            </AppShell>
          </ProtectedRoute>
        }
      />
      <Route
        path="/profile"
        element={
          <ProtectedRoute>
            <AppShell>
              <ProfilePage />
            </AppShell>
          </ProtectedRoute>
        }
      />
      <Route path="*" element={<NotFoundPage />} />
    </Routes>
  );
}

export default function App() {
  return (
    <AuthProvider>
      <AppRoutes />
    </AuthProvider>
  );
}
