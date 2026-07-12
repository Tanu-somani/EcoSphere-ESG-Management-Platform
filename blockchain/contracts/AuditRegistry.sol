// SPDX-License-Identifier: MIT
pragma solidity ^0.8.19;

/// @title AuditRegistry
/// @notice Minimal on-chain registry for tamper-proof ESG audit proofs.
///         Stores only a hash + metadata per audit — no tokens, no NFTs, no DAO.
contract AuditRegistry {
    struct AuditProof {
        string auditId;
        bytes32 dataHash;
        uint256 timestamp;
        address submittedBy;
    }

    // auditId => proof
    mapping(string => AuditProof) private auditProofs;
    mapping(string => bool) private auditExists;

    event AuditProofStored(
        string indexed auditId,
        bytes32 dataHash,
        uint256 timestamp,
        address indexed submittedBy
    );

    /// @notice Store the hash of a finalized audit. Can only be done once per auditId.
    function storeAuditProof(string calldata auditId, bytes32 dataHash) external {
        require(bytes(auditId).length > 0, "auditId required");
        require(!auditExists[auditId], "Audit proof already exists");

        auditProofs[auditId] = AuditProof({
            auditId: auditId,
            dataHash: dataHash,
            timestamp: block.timestamp,
            submittedBy: msg.sender
        });
        auditExists[auditId] = true;

        emit AuditProofStored(auditId, dataHash, block.timestamp, msg.sender);
    }

    /// @notice Fetch the stored proof for an audit.
    function getAuditProof(string calldata auditId)
        external
        view
        returns (
            string memory _auditId,
            bytes32 _dataHash,
            uint256 _timestamp,
            address _submittedBy
        )
    {
        require(auditExists[auditId], "Audit proof does not exist");
        AuditProof memory proof = auditProofs[auditId];
        return (proof.auditId, proof.dataHash, proof.timestamp, proof.submittedBy);
    }

    /// @notice Compare a freshly computed hash against the one stored on-chain.
    function verifyAudit(string calldata auditId, bytes32 currentHash)
        external
        view
        returns (bool matches)
    {
        require(auditExists[auditId], "Audit proof does not exist");
        return auditProofs[auditId].dataHash == currentHash;
    }

    /// @notice Convenience check so the backend doesn't have to catch a revert.
    function proofExists(string calldata auditId) external view returns (bool) {
        return auditExists[auditId];
    }
}