import { UserCircle2, Sparkles } from 'lucide-react';

export default function ProfilePage() {
  return (
    <div className="container">
      <div className="page-card">
        <div className="badge" style={{ marginBottom: 10 }}><UserCircle2 size={14} /> Profile</div>
        <h1 style={{ margin: '0 0 8px' }}>Your EcoSphere profile</h1>
        <p className="muted">View your account information, role, and engagement signals in one clear place.</p>
        <div className="grid grid-2" style={{ marginTop: 20 }}>
          <div className="page-card">
            <div className="badge"><Sparkles size={14} /> Account</div>
            <h3 style={{ marginBottom: 6 }}>Name</h3>
            <p style={{ margin: 0 }}>EcoSphere User</p>
          </div>
          <div className="page-card">
            <div className="badge"><Sparkles size={14} /> Engagement</div>
            <h3 style={{ marginBottom: 6 }}>Current XP</h3>
            <p style={{ margin: 0 }}>3,420</p>
          </div>
        </div>
      </div>
    </div>
  );
}
