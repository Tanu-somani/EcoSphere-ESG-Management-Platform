import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { ArrowRight, Sparkles } from 'lucide-react';
import { useAuth } from '../context/AuthContext';

export default function SignupPage() {
  const navigate = useNavigate();
  const { signup } = useAuth();
  const [form, setForm] = useState({ name: '', email: '', password: '', department: '' });
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  const handleSubmit = async (event) => {
    event.preventDefault();
    setLoading(true);
    setError('');
    setSuccess('');

    try {
      await signup(form);
      setSuccess('Account created successfully. You can now sign in.');
      setTimeout(() => navigate('/login'), 800);
    } catch (err) {
      setError(err?.response?.data?.message || 'We could not create the account.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="container" style={{ padding: '32px 0' }}>
      <div className="page-card" style={{ maxWidth: 720, margin: '0 auto' }}>
        <div className="badge" style={{ marginBottom: '12px' }}><Sparkles size={14} /> Join EcoSphere</div>
        <h1 style={{ margin: '0 0 10px', fontSize: '2rem' }}>Create your ESG workspace</h1>
        <p className="muted">Register with the details your backend expects and start managing sustainability impact with confidence.</p>

        <form onSubmit={handleSubmit} className="form-grid" style={{ marginTop: 20 }}>
          {error ? <div className="list-item" style={{ color: '#b42318' }}>{error}</div> : null}
          {success ? <div className="list-item" style={{ color: '#0f766e' }}>{success}</div> : null}

          <div className="grid grid-2">
            <div>
              <label className="label" htmlFor="name">Full name</label>
              <input id="name" className="input" value={form.name} onChange={(e) => setForm({ ...form, name: e.target.value })} required />
            </div>
            <div>
              <label className="label" htmlFor="email">Work email</label>
              <input id="email" className="input" type="email" value={form.email} onChange={(e) => setForm({ ...form, email: e.target.value })} required />
            </div>
          </div>

          <div className="grid grid-2">
            <div>
              <label className="label" htmlFor="password">Password</label>
              <input id="password" className="input" type="password" value={form.password} onChange={(e) => setForm({ ...form, password: e.target.value })} required />
            </div>
            <div>
              <label className="label" htmlFor="department">Department</label>
              <input id="department" className="input" value={form.department} onChange={(e) => setForm({ ...form, department: e.target.value })} />
            </div>
          </div>

          <button className="btn btn-primary" type="submit" disabled={loading}>{loading ? 'Creating account…' : <>Create account <ArrowRight size={16} /></>}</button>
          <p className="muted">Already have an account? <Link to="/login">Sign in</Link></p>
        </form>
      </div>
    </div>
  );
}
