import { ShieldCheck, Sparkles, Search } from 'lucide-react';

const items = [
  { title: 'Policies acknowledged', value: '96%', detail: 'Team-wide compliance coverage' },
  { title: 'Open audits', value: '4', detail: 'Pending executive review' },
  { title: 'Compliance issues', value: '2', detail: 'Escalated for remediation' },
];

export default function GovernancePage() {
  return (
    <div className="container">
      <div className="page-card" style={{ marginBottom: 20 }}>
        <div className="badge" style={{ marginBottom: 10 }}><ShieldCheck size={14} /> Governance & Compliance</div>
        <h1 style={{ margin: '0 0 8px' }}>Trust, transparency, and control</h1>
        <p className="muted">Manage policies, acknowledgements, audits, and compliance workstreams in a secure, clear interface.</p>
      </div>

      <div className="grid grid-3">
        {items.map((item) => (
          <div key={item.title} className="page-card">
            <div className="badge"><Sparkles size={14} /> Governance</div>
            <h3 style={{ marginBottom: 6 }}>{item.title}</h3>
            <p style={{ fontSize: '1.35rem', fontWeight: 700, margin: '6px 0' }}>{item.value}</p>
            <p className="muted" style={{ margin: 0 }}>{item.detail}</p>
          </div>
        ))}
      </div>

      <div className="page-card" style={{ marginTop: 20 }}>
        <div className="row-between">
          <strong>Governance actions</strong>
          <div style={{ position: 'relative', width: 'min(260px, 100%)' }}>
            <Search size={16} style={{ position: 'absolute', left: 12, top: 12, color: '#94a3b8' }} />
            <input className="input" style={{ paddingLeft: 36 }} placeholder="Find policy" />
          </div>
        </div>
        <div className="list" style={{ marginTop: 14 }}>
          <div className="list-item">Code of conduct acknowledgement due this week.</div>
          <div className="list-item">Privacy policy review scheduled.</div>
          <div className="list-item">Supplier governance check completed.</div>
        </div>
      </div>
    </div>
  );
}
