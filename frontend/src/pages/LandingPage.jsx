import { ArrowRight, BarChart3, Leaf, ShieldCheck, Sparkles, Users } from 'lucide-react';
import { Link } from 'react-router-dom';

export default function LandingPage() {
  return (
    <div className="container">
      <div className="hero">
        <div className="page-card">
          <div className="badge" style={{ marginBottom: 12 }}><Sparkles size={14} /> EcoSphere Preview</div>
          <h1>Intelligence for a sustainable future.</h1>
          <p>Bring together environmental performance, social impact, governance health, and employee engagement into one polished operating system for ESG leadership.</p>
          <div style={{ display: 'flex', gap: 12, flexWrap: 'wrap' }}>
            <Link to="/dashboard" className="btn btn-primary">Enter EcoSphere <ArrowRight size={16} /></Link>
            <Link to="/reports" className="btn btn-secondary">View reports</Link>
          </div>
        </div>

        <div className="page-card">
          <div className="row-between" style={{ marginBottom: 14 }}>
            <strong>Impact Pulse</strong>
            <span className="badge">Live</span>
          </div>
          <div className="grid">
            <div className="metric-card"><strong>Overall ESG</strong><div style={{ fontSize: '1.7rem', marginTop: 8 }}>87.4</div></div>
            <div className="grid grid-2">
              <div className="metric-card violet"><Leaf size={16} /> <div style={{ marginTop: 8 }}>Env</div><strong>91%</strong></div>
              <div className="metric-card"><Users size={16} /> <div style={{ marginTop: 8 }}>Social</div><strong>76%</strong></div>
              <div className="metric-card navy"><ShieldCheck size={16} /> <div style={{ marginTop: 8 }}>Governance</div><strong>89%</strong></div>
              <div className="metric-card gold"><BarChart3 size={16} /> <div style={{ marginTop: 8 }}>Challenges</div><strong>12 active</strong></div>
            </div>
          </div>
        </div>
      </div>

      <div className="grid grid-3" style={{ marginTop: 24 }}>
        <div className="page-card">
          <h3>Enterprise-ready workflows</h3>
          <p className="muted">From carbon reporting to policy acknowledgements, every workflow is designed for real operational use.</p>
        </div>
        <div className="page-card">
          <h3>Modern ESG analytics</h3>
          <p className="muted">Turn complex sustainability datasets into trusted executive decisions with a focused intelligence layer.</p>
        </div>
        <div className="page-card">
          <h3>Engaged teams</h3>
          <p className="muted">Incentivize behavior with challenges, badges, XP, and leaderboards that feel premium and purposeful.</p>
        </div>
      </div>
    </div>
  );
}
