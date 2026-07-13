import { ArrowLeft, Compass } from 'lucide-react';
import { Link } from 'react-router-dom';

export default function NotFoundPage() {
  return (
    <div className="container" style={{ paddingTop: 48 }}>
      <div className="page-card" style={{ maxWidth: 640, margin: '0 auto', textAlign: 'center' }}>
        <div className="badge" style={{ marginBottom: 12 }}><Compass size={14} /> Page not found</div>
        <h1 style={{ margin: '0 0 8px' }}>The route you requested is unavailable.</h1>
        <p className="muted">Return to EcoSphere and continue your ESG journey from the dashboard.</p>
        <Link to="/dashboard" className="btn btn-primary" style={{ marginTop: 12 }}><ArrowLeft size={16} /> Back to dashboard</Link>
      </div>
    </div>
  );
}
