import { FileText, Search, Sparkles } from 'lucide-react';

export default function ReportsPage() {
  return (
    <div className="container">
      <div className="page-card" style={{ marginBottom: 20 }}>
        <div className="badge" style={{ marginBottom: 10 }}><FileText size={14} /> Reports & Exports</div>
        <h1 style={{ margin: '0 0 8px' }}>Environmental, social, governance, and ESG summary reporting</h1>
        <p className="muted">Generate, filter, and export reports using backend-supported criteria and time ranges.</p>
      </div>

      <div className="page-card">
        <div className="row-between" style={{ marginBottom: 14 }}>
          <strong>Available reports</strong>
          <div style={{ position: 'relative', width: 'min(260px, 100%)' }}>
            <Search size={16} style={{ position: 'absolute', left: 12, top: 12, color: '#94a3b8' }} />
            <input className="input" style={{ paddingLeft: 36 }} placeholder="Filter reports" />
          </div>
        </div>
        <div className="list">
          <div className="list-item"><div className="row-between"><strong>Environmental summary</strong><span className="badge"><Sparkles size={14} /> Exportable</span></div></div>
          <div className="list-item"><div className="row-between"><strong>Social performance</strong><span className="badge"><Sparkles size={14} /> Exportable</span></div></div>
          <div className="list-item"><div className="row-between"><strong>Governance compliance</strong><span className="badge"><Sparkles size={14} /> Exportable</span></div></div>
        </div>
      </div>
    </div>
  );
}
