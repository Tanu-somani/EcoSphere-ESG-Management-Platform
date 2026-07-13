import { HeartHandshake, Search, Sparkles } from 'lucide-react';

const items = [
  { title: 'Volunteer participation', value: '74%', detail: 'Employees engaged this quarter' },
  { title: 'Training completion', value: '92%', detail: 'Mandatory courses completed' },
  { title: 'Diversity initiatives', value: '18', detail: 'Programs active across teams' },
];

export default function SocialPage() {
  return (
    <div className="container">
      <div className="page-card" style={{ marginBottom: 20 }}>
        <div className="badge" style={{ marginBottom: 10 }}><HeartHandshake size={14} /> Social Impact</div>
        <h1 style={{ margin: '0 0 8px' }}>Social initiatives and employee engagement</h1>
        <p className="muted">Connect CSR activity, participation, and training outcomes with a calm, executive-friendly experience.</p>
      </div>

      <div className="grid grid-3">
        {items.map((item) => (
          <div key={item.title} className="page-card">
            <div className="badge"><Sparkles size={14} /> Social</div>
            <h3 style={{ marginBottom: 6 }}>{item.title}</h3>
            <p style={{ fontSize: '1.35rem', fontWeight: 700, margin: '6px 0' }}>{item.value}</p>
            <p className="muted" style={{ margin: 0 }}>{item.detail}</p>
          </div>
        ))}
      </div>

      <div className="page-card" style={{ marginTop: 20 }}>
        <div className="row-between">
          <strong>Recent social activities</strong>
          <div style={{ position: 'relative', width: 'min(260px, 100%)' }}>
            <Search size={16} style={{ position: 'absolute', left: 12, top: 12, color: '#94a3b8' }} />
            <input className="input" style={{ paddingLeft: 36 }} placeholder="Search activity" />
          </div>
        </div>
        <div className="list" style={{ marginTop: 14 }}>
          <div className="list-item">Community support program launched.</div>
          <div className="list-item">New employee wellness challenge accepted.</div>
          <div className="list-item">Mandatory training completion exceeded target.</div>
        </div>
      </div>
    </div>
  );
}
