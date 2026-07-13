import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { Eye, EyeOff, Lock, Mail, Sparkles } from 'lucide-react';
import { useAuth } from '../context/AuthContext';

export default function LoginPage() {
  const navigate = useNavigate();
  const { login } = useAuth();
  const [form, setForm] = useState({ email: '', password: '' });
  const [showPassword, setShowPassword] = useState(false);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();
    setLoading(true);
    setError('');

    try {
      await login(form);
      navigate('/preview');
    } catch (err) {
      setError(err?.response?.data?.message || 'Sign-in failed. Please verify your credentials.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container" style={{ padding: '32px 0' }}>
      <div className="auth-card">
        <div className="auth-panel">
          <div className="badge" style={{ marginBottom: '14px' }}><Sparkles size={14} /> Enterprise ESG Intelligence</div>
          <h1 style={{ margin: '0 0 10px', fontSize: '2rem' }}>Access EcoSphere</h1>
          <p className="muted" style={{ marginBottom: '24px' }}>Monitor climate, social, and governance performance from a single trusted workspace.</p>

          <form onSubmit={handleSubmit} className="form-grid">
            {error ? <div className="list-item" style={{ color: '#b42318' }}>{error}</div> : null}

            <div>
              <label className="label" htmlFor="email">Work email</label>
              <div style={{ position: 'relative' }}>
                <Mail size={16} style={{ position: 'absolute', left: 12, top: 13, color: '#94a3b8' }} />
                <input id="email" className="input" style={{ paddingLeft: 40 }} type="email" value={form.email} onChange={(e) => setForm({ ...form, email: e.target.value })} required />
              </div>
            </div>

            <div>
              <label className="label" htmlFor="password">Password</label>
              <div style={{ position: 'relative' }}>
                <Lock size={16} style={{ position: 'absolute', left: 12, top: 13, color: '#94a3b8' }} />
                <input id="password" className="input" style={{ paddingLeft: 40, paddingRight: 40 }} type={showPassword ? 'text' : 'password'} value={form.password} onChange={(e) => setForm({ ...form, password: e.target.value })} required />
                <button type="button" onClick={() => setShowPassword(!showPassword)} style={{ position: 'absolute', right: 10, top: 10, border: 'none', background: 'transparent' }}>
                  {showPassword ? <EyeOff size={16} /> : <Eye size={16} />}
                </button>
              </div>
            </div>

            <div className="row-between">
              <label><input type="checkbox" /> Remember me</label>
              <Link to="/signup" className="muted">Create account</Link>
            </div>

            <button className="btn btn-primary" type="submit" disabled={loading}>{loading ? 'Signing in…' : 'Sign in'}</button>
          </form>
        </div>

        <div className="auth-illustration">
          <div className="pill"><Sparkles size={14} /> Built for enterprise ESG leaders</div>
          <h2 style={{ margin: 0, fontSize: '1.6rem' }}>Turn sustainability data into confident action.</h2>
          <p className="muted" style={{ margin: 0 }}>Unify emissions, social impact, governance controls, and employee engagement in a single intelligent platform.</p>
          <div className="page-card" style={{ padding: 16 }}>
            <div className="row-between"><strong>Impact Pulse</strong><span className="badge">Live</span></div>
            <div className="list" style={{ marginTop: 12 }}>
              <div className="list-item">Environmental score: 88/100</div>
              <div className="list-item">Social engagement: 74%</div>
              <div className="list-item">Governance readiness: 92%</div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
