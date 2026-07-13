import { NavLink, useNavigate } from 'react-router-dom';
import { BarChart3, BriefcaseBusiness, FileText, Gauge, Home, Leaf, LogOut, ShieldCheck, Sparkles, UserCircle2, Users } from 'lucide-react';
import { useAuth } from '../../context/AuthContext';

const navItems = [
  { to: '/preview', label: 'Overview', icon: Home },
  { to: '/dashboard', label: 'Dashboard', icon: Gauge },
  { to: '/environmental', label: 'Environmental', icon: Leaf },
  { to: '/social', label: 'Social', icon: Users },
  { to: '/governance', label: 'Governance', icon: ShieldCheck },
  { to: '/impact-hub', label: 'Impact Hub', icon: Sparkles },
  { to: '/reports', label: 'Reports', icon: FileText },
  { to: '/settings', label: 'Settings', icon: BriefcaseBusiness },
  { to: '/profile', label: 'Profile', icon: UserCircle2 },
];

export default function AppShell({ children }) {
  const navigate = useNavigate();
  const { logout, user } = useAuth();

  return (
    <div className="app-shell">
      <aside className="sidebar">
        <div className="brand-block">
          <div className="brand-mark"><BarChart3 size={18} /></div>
          <div>
            <p className="brand-label">EcoSphere</p>
            <p className="brand-sub">ESG Intelligence</p>
          </div>
        </div>

        <nav className="nav-list">
          {navItems.map(({ to, label, icon: Icon }) => (
            <NavLink key={to} to={to} className={({ isActive }) => `nav-link ${isActive ? 'active' : ''}`}>
              <Icon size={18} />
              <span>{label}</span>
            </NavLink>
          ))}
        </nav>

        <div className="sidebar-footer">
          <div className="profile-card">
            <p className="muted">Signed in as</p>
            <strong>{user?.name || user?.email || 'EcoSphere User'}</strong>
          </div>
          <button className="ghost-button" onClick={() => { logout(); navigate('/login'); }}>
            <LogOut size={16} />
            Logout
          </button>
        </div>
      </aside>

      <main className="main-content">
        {children}
      </main>
    </div>
  );
}
