TITLE OPROppCodeTwo

.code

SMAOppCode PROC uses eax
	mov eax , Accumulator
	cmp eax , 0d
	jc LESSTHANZERO
	jmp ZERO
LESSTHANZERO:
	mov eax , ProgramCounter
	inc eax
	mov ProgramCounter , eax
	ret
ZERO:
	ret
SMAOppCode ENDP

SZAOppCode PROC uses eax
	mov eax , Accumulator
	cmp eax , 0d
	jz ZERO
	jmp NOTZERO
ZERO:
	mov eax , ProgramCounter
	inc eax
	mov ProgramCounter , eax
	ret
NOTZERO:
	ret
SZAOppCode ENDP

SNLOppCode PROC uses eax
	mov eax , Link
	cmp eax , 0d
	jnz NOTZERO
	jmp ZERO
ZERO:
	ret
NOTZERO:
	mov eax , ProgramCounter
	inc eax
	mov ProgramCounter , eax
	ret
SNLOppCode ENDP

SKPOppCode PROC uses eax
	mov eax , ProgramCounter
	inc eax
	mov ProgramCounter , eax
	ret
SKPOppCode ENDP

SPAOppCode PROC uses eax
	mov eax , Accumulator
	cmp eax , 0d
	jnz GREATERTHANZERO
	jmp ZERO
GREATERTHANZERO:
	mov eax , ProgramCounter
	inc eax
	mov ProgramCounter , eax
	ret
ZERO:
	ret
SPAOppCode ENDP

SNAOppCode PROC uses eax
	mov eax , Accumulator
	cmp eax , 0d
	jnz NOTZERO
	jmp ZERO
ZERO:
	ret
NOTZERO:
	mov eax , ProgramCounter
	inc eax
	mov ProgramCounter , eax
	ret
SNAOppCode ENDP

SZLOppCode PROC uses eax
	mov eax , Link
	cmp eax , 0d
	jz ZERO
	jmp NOTZERO
ZERO:
	mov eax , ProgramCounter
	inc eax
	mov ProgramCounter , eax
	ret
NOTZERO:
	ret
SZLOppCode ENDP

OSROppCode PROC uses edx
	mov edx , OFFSET ExecuteOppCodeMessageOne
	call WriteString
	ret
OSROppCode ENDP

HLTOppCode PROC uses eax
	exit
HLTOppCode ENDP